package rent.domain.renting;

import rent.repo.api.Repositories;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Renting {
    private final long userId;
    private final transient Repositories repositories;

    public Renting(int userId, Repositories repositories) {
        this.repositories = repositories;
        this.userId = userId;
    }

    List<Agreement> getRentingAgreement(){
        return repositories.getRentingRepository().getAgreements(userId).stream().map(agreementDto -> new Agreement(agreementDto)).collect(toList());
    }

}
