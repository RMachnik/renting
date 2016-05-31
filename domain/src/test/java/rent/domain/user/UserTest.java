package rent.domain.user;

import org.junit.Test;
import rent.repo.stationary.StaticRepositories;

import static org.fest.assertions.Assertions.assertThat;
import static rent.repo.stationary.user.StaticUserDto.USER_DTO;

public class UserTest {

    @Test
    public void shouldConstructProperUser() {
        User user = new User(USER_DTO.getEmail(), USER_DTO.getPassword(), new StaticRepositories());

        assertThat(user.getId()).isEqualTo(USER_DTO.getId());
        assertThat(user.getFirstName()).isEqualTo(USER_DTO.getFirstName());
        assertThat(user.getLastName().get()).isEqualTo(USER_DTO.getLastName());
        assertThat(user.getEmail()).isEqualTo(new Email(USER_DTO.getEmail()));
        assertThat(user.getPassword()).isEqualTo(USER_DTO.getPassword());
        assertThat(user.isActive()).isEqualTo(USER_DTO.isActive());
        assertThat(user.getMainContactDetails().isPresent()).isTrue();
        assertThat(user.getInvoiceContactDetails().isPresent()).isTrue();
    }
}
