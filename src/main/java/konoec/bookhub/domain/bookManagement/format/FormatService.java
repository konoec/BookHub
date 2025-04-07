package konoec.bookhub.domain.bookManagement.format;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormatService {
    private final FormatRepository formatRepository;

    public List<Format> findAll() {
        return formatRepository.findAll();
    }

    public Format findById(Long id) {
        return formatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formato no encontrado"));
    }

    public Format save(Format format) {
        return formatRepository.save(format);
    }

    public Format update(Long id, Format format) {
        return formatRepository.findById(id)
                .map(existingFormat -> {
                    existingFormat.setName(format.getName());
                    return formatRepository.save(existingFormat);
                })
                .orElseThrow(() -> new RuntimeException("Formato no encontrado"));
    }

    public void delete(Long id) {
        formatRepository.findById(id)
                .map(format -> {
                    format.setIsDeleted(true);
                    return formatRepository.save(format);
                })
                .orElseThrow(() -> new RuntimeException("Formato no encontrado"));
    }

    public void activate(Long id) {
        formatRepository.findById(id)
                .map(format -> {
                    format.setIsDeleted(false);
                    return formatRepository.save(format);
                })
                .orElseThrow(() -> new RuntimeException("Formato no encontrado"));
    }
}
