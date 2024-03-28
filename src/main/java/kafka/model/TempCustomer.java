package kafka.model;

public class TempCustomer {
    private int age;
    private boolean automatedEmail;
    private String firstName;
    private String lastName;
    private float height;
    private float weight;

    TempCustomer(int age, boolean automatedEmail, String firstName, String lastName, float height, float weight) {
        this.age = age;
        this.automatedEmail = automatedEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
    }

    public static TempCustomerBuilder builder() {
        return new TempCustomerBuilder();
    }

    public int getAge() {
        return this.age;
    }

    public boolean isAutomatedEmail() {
        return this.automatedEmail;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public float getHeight() {
        return this.height;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAutomatedEmail(boolean automatedEmail) {
        this.automatedEmail = automatedEmail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public static class TempCustomerBuilder {
        private int age;
        private boolean automatedEmail;
        private String firstName;
        private String lastName;
        private float height;
        private float weight;

        TempCustomerBuilder() {
        }

        public TempCustomerBuilder age(int age) {
            this.age = age;
            return this;
        }

        public TempCustomerBuilder automatedEmail(boolean automatedEmail) {
            this.automatedEmail = automatedEmail;
            return this;
        }

        public TempCustomerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public TempCustomerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public TempCustomerBuilder height(float height) {
            this.height = height;
            return this;
        }

        public TempCustomerBuilder weight(float weight) {
            this.weight = weight;
            return this;
        }

        public TempCustomer build() {
            return new TempCustomer(this.age, this.automatedEmail, this.firstName, this.lastName, this.height, this.weight);
        }

        public String toString() {
            return "TempCustomer.TempCustomerBuilder(age=" + this.age + ", automatedEmail=" + this.automatedEmail + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", height=" + this.height + ", weight=" + this.weight + ")";
        }
    }
}
