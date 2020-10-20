package by.realovka.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegDTO {

    @NotBlank
    @NotEmpty
    private String nameUserDTO;
    @NotBlank
    @NotEmpty
    private String loginUserDTO;
    @NotBlank
    @NotEmpty
    private String passwordUserDTO;

}
