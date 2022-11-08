package kg.ssb.sewing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeUuid;
    private String masterUuid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;
    private String action;
    private String cause;
    private double rating;
    private double fine;

    public EmployeeDetail(String employeeUuid, String masterUuid, String action, String cause, double rating, double fine) {
        this.employeeUuid = employeeUuid;
        this.masterUuid = masterUuid;
        this.action = action;
        this.cause = cause;
        this.rating = rating;
        this.fine = fine;
    }
}
