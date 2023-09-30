package enums;

import io.github.bucket4j.Bucket;

public class PricingPlanMain {

    public static void main(String[] args) {
        String apiKey = "JASDGFGADSGFADSHGYUERWTHJBDHJGU8WYQ487";
        PricingPlan pricingPlan = PricingPlan.resolvePlanFromApiKey(apiKey);
        Bucket bucket = Bucket.builder()
                .addLimit(pricingPlan.getLimit())
                .build();


    }
}
