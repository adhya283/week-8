abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class LeaveRequest {
    Employee employee;
    String startDate, endDate;
    String status = "Pending";

    LeaveRequest(Employee employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    void approve() {
        if (status.equals("Pending")) {
            status = "Approved";
            System.out.println(employee.name + "'s leave request (" +
                    startDate + "-" + endDate + ") approved.");
            System.out.println("Status: " + status);
        }
    }

    void reject() {
        if (status.equals("Pending")) {
            status = "Rejected";
            System.out.println(employee.name + "'s leave request (" +
                    startDate + "-" + endDate + ") rejected.");
            System.out.println("Status: " + status);
        }
    }

    void changeToPending() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to Pending.");
        }
    }
}

public class EmployeeLeave {
    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 =
                new LeaveRequest(john, "Jan 1", "Jan 5");

        System.out.println("Leave request submitted for John (Jan 1-5).");
        System.out.println("Status: " + r1.status);

        r1.approve();

        LeaveRequest r2 =
                new LeaveRequest(jane, "Feb 10", "Feb 11");

        System.out.println("Leave request submitted for Jane (Feb 10-11).");
        System.out.println("Status: " + r2.status);

        r2.reject();

        r1.changeToPending();
    }
}