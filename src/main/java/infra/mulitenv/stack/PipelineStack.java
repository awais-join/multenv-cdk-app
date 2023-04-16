package infra.mulitenv.stack;

import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.StageProps;
import software.amazon.awscdk.pipelines.*;
import software.amazon.awscdk.services.lambda.Code;
import software.constructs.Construct;

import java.util.Arrays;

public class PipelineStack extends Stack {

    public PipelineStack(Construct scope, String id, final StackProps props) {
        super(scope, id, props);
        CodePipeline pipeline = CodePipeline.Builder.create(this, "Pipeline")
                .pipelineName("MultiEnvPipeline")
                .synth(new ShellStep("Synth", ShellStepProps.builder()
                        .input(CodePipelineSource.gitHub("awais-join/multenv-cdk-app", "master"))
                        .commands(Arrays.asList("npm ci", "npm run build", "npx cdk synth"))
                        .build())
                )
//                .selfMutation(true)
                .build();

        Environment devEnvironment = Environment.builder()
                .account("808354265930")
                .region("us-east-1")
                .build();

        pipeline.addStage(new PipelineAppStage(scope, "DEV", StageProps.builder()
                .env(devEnvironment)
                .build()));
    }
}
