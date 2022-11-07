package kg.ssb.sewing.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetail {
    @Id
    private String employeeUuid;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employeeUuid")
    private List<EmployeeWorkingTime> workingTimeList = new ArrayList<>();

    public EmployeeDetail(String employeeUuid) {
        this.employeeUuid = employeeUuid;
    }
}
