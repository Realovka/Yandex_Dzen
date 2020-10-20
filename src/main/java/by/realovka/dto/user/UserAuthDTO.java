package by.realovka.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDTO {

    @NotBlank
    @NotEmpty
    private String loginAuthUser;
    @NotBlank
    @NotEmpty
    private String passwordAuthUser;

}
