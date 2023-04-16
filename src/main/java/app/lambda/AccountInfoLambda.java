package app.lambda;

import software.amazon.awssdk.regions.Region;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityRequest;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityResponse;

public class AccountInfoLambda implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        // Create an STS client using the default region
        StsClient stsClient = StsClient.builder().region(Region.of(Region.US_EAST_1.toString())).build();

        // Create a GetCallerIdentityRequest to get the AWS account ID and ARN
        GetCallerIdentityRequest request = GetCallerIdentityRequest.builder().build();
        GetCallerIdentityResponse response = stsClient.getCallerIdentity(request);

        // Extract the AWS account ID and ARN from the response
        String accountId = response.account();
        String arn = response.arn();

        // Extract the AWS account name from the ARN
        String accountName = arn.split(":")[4];

        // Extract the AWS region from the ARN
        String region = arn.split(":")[3];

        // Return the AWS account name and region as a string
        return "AWS Account Name: " + accountName + ", AWS Region: " + region;
    }
}

