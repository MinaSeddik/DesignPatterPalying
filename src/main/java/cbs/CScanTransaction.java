package cbs;

public abstract class CScanTransaction extends Transaction {

    private TransactionType transactionType = TransactionType.REGULAR_TRANSACTION;
    private TransactionRateType transactionRateType;

    public CScanTransaction(TransactionData transactionData) {
        super(transactionData);

        categorizeTransaction();
    }

    @Override
    public void calculateTransactionFee() {
        calculateCertifixFee();
        calculateGovFee();
    }


    private void categorizeTransaction() {
//        transactionType = transactionData.getAccountNumber() &&
//                transactionData.getAccount() &&
//                transactionData.getAccountNumber() == transactionData.getAccount().getNumber() &&
//                transactionData.getAccount().getSatatus == AccountType.AUTOMATIC_ACTIVE
//                && ..... ? TransactionType.ON_ACCOUNT : TransactionType.REGULAR_TRANSACTION;

    }


    public void calculateCertifixFee() {

    }

    public void calculateGovFee() {

    }


}
