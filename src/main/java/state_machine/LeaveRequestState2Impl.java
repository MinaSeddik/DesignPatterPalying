package state_machine;

public class LeaveRequestState2Impl implements LeaveRequestState2{
    @Override
    public void next(LeaveRequest leaveRequest) {
//        leaveRequest.setState(new DeliveredState());
    }

    @Override
    public void prev(LeaveRequest leaveRequest) {

    }

    @Override
    public void printStatus() {

    }
}
