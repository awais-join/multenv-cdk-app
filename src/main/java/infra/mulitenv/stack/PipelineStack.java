package infra.mulitenv.stack;

import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.StageProps;
import software.amazon.awscdk.pipelines.*;
import software.constructs.Construct;

import java.util.Arrays;

public class PipelineStack extends Stack {

    public PipelineStack(Construct scope, String id, final StackProps props) {
        super(scope, id, props);
        CodePipeline pipeline = CodePipeline.Builder.create(scope, "Pipeline")
                .pipelineName("MultiEnvPipeline")
                .synth(new ShellStep("Synth", ShellStepProps.builder()
                        .input(CodePipelineSource.gitHub("awais-join/multi-env-cdk-demo", "master"))
                        .commands(Arrays.asList("cdk synth"))
                        .build())
                )
                .selfMutation(true)
                .build();

        pipeline.addStage(new PipelineAppStage(this, "DEV", StageProps.builder()
                .env(Environment.builder().account("808354265930").region("us-east-1").build())
                .build()));
    }
}
