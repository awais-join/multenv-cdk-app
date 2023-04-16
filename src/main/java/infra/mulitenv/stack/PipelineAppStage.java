package infra.mulitenv.stack;

import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.constructs.Construct;

public class PipelineAppStage extends Stage {

    public PipelineAppStage(Construct scope, String id, final StageProps props) {
        super(scope, id, props);

        LambdaStack lambdaStack = new LambdaStack(this, "LambdaStack", null);

    }
}
