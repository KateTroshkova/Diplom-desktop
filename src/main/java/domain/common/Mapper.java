package domain.common;

public interface Mapper<Business, Dto> {

    Dto fromBusiness(Business business);

    Business fromDto(Dto dto);
}