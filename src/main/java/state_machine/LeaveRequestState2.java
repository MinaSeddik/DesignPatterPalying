package state_machine;

public interface LeaveRequestState2 {


    void next(LeaveRequest pkg);

    void prev(LeaveRequest pkg);

    void printStatus();

}
