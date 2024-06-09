package nsu.trushkov.TouristClub.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTo {
    private String email;
    private String password;
    private String role;

}
