package exception;

public class CheckedvsUnChecked {

    public static void main(String[] args) {

        CheckedvsUnChecked sut = new CheckedvsUnChecked();
        sut.doIt_checked();
    }

    private  void doIt_checked() {
        int x= 0;

        if(x==1){
            throw new RuntimeException("message");
        }

    }
}
