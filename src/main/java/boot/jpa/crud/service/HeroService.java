package boot.jpa.crud.service;

import boot.jpa.crud.domain.hero.Hero;
import boot.jpa.crud.domain.hero.HeroRepository;
import boot.jpa.crud.dto.HeroFindAllResponseDto;
import boot.jpa.crud.dto.HeroFindByIdResponseDto;
import boot.jpa.crud.dto.HeroSaveRequestDto;
import boot.jpa.crud.dto.HeroUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
// @AllArgsConstructor : 전체생성자
@AllArgsConstructor
public class HeroService {

    private HeroRepository heroRepository;

    @Transactional
    public Long HeroSaveRequest(HeroSaveRequestDto dto) {
        return heroRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    @ReadOnlyProperty
    public List<HeroFindAllResponseDto> HeroFindAllResponse() {
        // stream
        // stream().map(dto) : dto에 맞게 자동으로 매핑
        // .collect(Collectors.toList()) : List형태로 변환
        return heroRepository.findAll().stream()
                .map(HeroFindAllResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public HeroFindByIdResponseDto HeroFindByIdResponse(Long id) {
        Hero hero = heroRepository.findById(id).orElse(null);
        return new HeroFindByIdResponseDto(hero);
    }

    @Transactional
    public Long HeroUpdateRequest(HeroUpdateRequestDto dto) {
        return heroRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public void HeroDeleteByIdRequest(Long id) {
        heroRepository.deleteById(id);
    }





}
