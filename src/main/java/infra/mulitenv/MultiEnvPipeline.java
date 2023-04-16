package infra.mulitenv;

import infra.mulitenv.stack.PipelineStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class MultiEnvPipeline {
    public static void main(final String[] args) {
        App app = new App();

        Environment toolingEnvironment = Environment.builder()
                .account("808354265930")
                .region("us-east-1")
                .build();

        new PipelineStack(app, "PipelineStack", StackProps.builder().env(toolingEnvironment).build());
    }
}
