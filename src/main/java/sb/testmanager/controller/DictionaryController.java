package sb.testmanager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sb.testmanager.model.TestRunStatusDict;
import sb.testmanager.repository.DictionaryRepository;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/tm/dictionaries")
public class DictionaryController {

    private DictionaryRepository dictionaryRepository;

    @GetMapping
    public List<TestRunStatusDict> getDictionaries() {
        log.info("get test dictionaries");
        return dictionaryRepository.findAll();
    }
}
