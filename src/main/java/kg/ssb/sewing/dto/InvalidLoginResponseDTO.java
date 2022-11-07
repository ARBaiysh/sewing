package kg.ssb.sewing.dto;


import lombok.Getter;

@Getter
public class InvalidLoginResponseDTO {

    private String username;
    private String password;

    public InvalidLoginResponseDTO() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }


}

