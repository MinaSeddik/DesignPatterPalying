package state_machine;

public enum LeaveRequestState {

    Submitted {
        @Override
        public LeaveRequestState nextState() {

            if(responsiblePerson().equalsIgnoreCase("")){
                return Escalated;
            }
            return Escalated;
        }

        public LeaveRequestState approve() {

            if(responsiblePerson().equalsIgnoreCase("")){
                return Escalated;
            }
            return Escalated;
        }

        @Override
        public String responsiblePerson() {
            return "Employee";
        }
    },
    Escalated {
        @Override
        public LeaveRequestState nextState() {
            return Approved;
        }

        @Override
        public String responsiblePerson() {
            return "Team Leader";
        }
    },
    Approved {
        @Override
        public LeaveRequestState nextState() {
            return this;
        }

        @Override
        public String responsiblePerson() {
            return "Department Manager";
        }
    };

    public abstract LeaveRequestState nextState();
    public abstract String responsiblePerson();
}