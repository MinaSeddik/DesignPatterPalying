package spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Main {

    public static void main(String[] args) {

        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Any string'");
        String result = (String) expression.getValue();

        System.out.println("Result: " + result);


        Expression expression2 = expressionParser.parseExpression("'Any string'.length()");
        Integer result2 = (Integer) expression2.getValue();

        System.out.println("Result: " + result2);



        Expression expression3 = expressionParser.parseExpression("'Any string'.replace(\" \", \"\").length()");
        Integer result3 = (Integer) expression3.getValue();

        System.out.println("Result: " + result3);




        Car car = new Car();
        car.setMake("Good manufacturer");
        car.setModel("Model 3");
        car.setHorsePower(250);
        car.setYearOfProduction(2014);

        Engine engine = new Engine();
        engine.setCapacity(3500);

        car.setEngine(engine);

        Expression expression4 = expressionParser.parseExpression("model");

        EvaluationContext context = new StandardEvaluationContext(car);
        String result4 = (String) expression4.getValue(context);

        System.out.println("Result: " + result4);



        Expression expression5 = expressionParser.parseExpression(
//                "#{horsePower == 250 and engine.capacity lt 4000}");  // error
//                "horsePower == 250");   // ok
//                "engine.getCapacity() < 4000");  // ok
//                "horsePower == 250 && engine.getCapacity() < 4000");  // ok
                "horsePower == 250 && model.equalsIgnoreCase('Model 3')");

        EvaluationContext context5 = new StandardEvaluationContext(car);
        Boolean result5 = (Boolean) expression5.getValue(context5);

        System.out.println("Result: " + result5);



        Car car1 = new Car();
        car1.setMake("Good manufacturer");
        car1.setModel("Model 3");
        car1.setYearOfProduction(2014);

        CarPark carPark = new CarPark();
        carPark.getCars().add(car1);

        StandardEvaluationContext context6 = new StandardEvaluationContext(carPark);

        System.out.println("Before: " + carPark.getCars().get(0).getModel());

        expressionParser.parseExpression("cars[0].model")
                .setValue(context6, "Other model");

        System.out.println("After: " + carPark.getCars().get(0).getModel());

    }
}
