package state_machine;

public class LeaveRequest {

    private LeaveRequestState2 state = new LeaveRequestState2Impl();

    // getter, setter

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        state.printStatus();
    }


    public void approve() {
//        state.isAllowedAction(APPROVE);
//        state.isAuthorizedAction();
        state.next(this);
    }

}
