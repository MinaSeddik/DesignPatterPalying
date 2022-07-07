package cbs;

public abstract class Transaction {

    protected TransactionData transactionData;

    protected Account account;
    protected Operator operator;

    protected double rollingFee;
    protected double CertifixFee;
    protected double rollingCredit;

    protected double operatorDojGovFee;
    protected double accountDojGovFee;

    public Transaction(TransactionData transactionData) {
        this.transactionData = transactionData;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public abstract void calculateTransactionFee();
}
