import { ASTNode } from "@ballerina/ast-model";
import { ASTDidChangeParams, ASTDidChangeResponse, BallerinaASTNode, BallerinaEndpoint,
    BallerinaSourceFragment, GetASTParams, GetASTResponse  } from "@ballerina/lang-service";
import React from "react";
import { CommonDiagramProps, Diagram } from "./diagram";
import { DiagramContext, IDiagramContext } from "./diagram-context";
import { Loader } from "./loader";

export interface DiagramLangClient {
    getAST(params: GetASTParams): Thenable<GetASTResponse>;
    astDidChange(params: ASTDidChangeParams): Thenable<ASTDidChangeResponse>;
    parseFragment(params: BallerinaSourceFragment): Thenable<BallerinaASTNode>;
    getEndpoints(): Thenable<BallerinaEndpoint[]>;
    goToSource(line: number, column: number): void;
}

export interface EdiatableDiagramProps extends CommonDiagramProps {
    docUri: string;
    langClient: DiagramLangClient;
}

export interface EditableDiagramState {
    ast?: ASTNode;
    editingEnabled: boolean;
}

export class EditableDiagram extends React.Component<EdiatableDiagramProps, EditableDiagramState> {

    // get default context or provided context from a parent (if any)
    public static contextType = DiagramContext;

    public state = {
        ast: undefined,
        editingEnabled: true,
    };

    public render() {
        const { height, width, mode, zoom } = this.props;
        const { ast } = this.state;

        if (!ast) {
            return <Loader />;
        }

        // create props for the diagram
        const diagramProps = { height, width, mode, zoom, ast };

        return <DiagramContext.Provider value={this.createContext()}>
                <Diagram {...diagramProps} />
            </DiagramContext.Provider>;
    }

    public componentWillReceiveProps(nextProps: EdiatableDiagramProps) {
        if (this.props.docUri !== nextProps.docUri) {
            this.updateAST(nextProps.docUri);
        }
    }

    public componentDidMount(): void {
        this.updateAST();
    }

    public updateAST(uri: string = this.props.docUri) {
        // invoke the parser and get the AST
        this.props.langClient.getAST({
            documentIdentifier: {
                uri,
            },
        }).then((resp) => {
            if (resp.ast) {
            this.setState({
                ast: resp.ast,
            });
            }
        }, (err) => {
            // TODO Handle the error
        });
    }

    private createContext(): IDiagramContext {
        // create context contributions for the children
        const contextContributions = {
            editingEnabled: this.state.editingEnabled,
            toggleEditing: () => {
                this.setState({
                    editingEnabled: !this.state.editingEnabled,
                });
            },
        };

        // merge with parent (if any) or with default context
        return { ...this.context, ...contextContributions };
    }
}
