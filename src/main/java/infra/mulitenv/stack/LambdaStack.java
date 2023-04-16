package infra.mulitenv.stack;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.util.Map;

public class LambdaStack extends Stack {

    public LambdaStack(Construct scope, String id, final StackProps props) {
        super(scope, id, props);

        Function function = Function.Builder.create(this, "LambdaFunction")
                .runtime(Runtime.JAVA_11)
                .code(Code.fromAsset("target/multienv-0.1.jar"))
                .handler("app.lambda.AccountInfoLambda::handleRequest")
                .environment(Map.of("acctid", Stack.of(this).getAccount(), "region", Stack.of(this).getRegion()))
                .build();

        LambdaRestApi api = LambdaRestApi.Builder.create(this, "ApiGwEndpoint")
                .handler(function)
                .build();

    }

}
