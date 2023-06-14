package pl.sda.demo.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/interview")
public class InterviewController {

    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    private final QuestionRepository repository;

    public InterviewController(QuestionRepository repository) {
        this.repository = repository;
    }

    //main menu
    @GetMapping
    public String mainMenu(Model model) {
        model.addAttribute("name", "Interview Questions App");
        return "main";
    }

    //add new interview question with answer
    @GetMapping("/add")
    public String add() {
        return "new";
    }

    @GetMapping("/delete")
    public String deleteView(){
        return "delete";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String question, @RequestParam String answer, Model model) {
        Question savedQuestion = repository.save(new Question(question, answer));

        logger.info("Question: {} Answer: {}", question, answer);
        //return result view
        model.addAttribute("id", savedQuestion.getId());
        model.addAttribute("question", savedQuestion.getQuestion());
        model.addAttribute("answer", savedQuestion.getAnswer());
        return "result";
    }

    //delete question
    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        if (repository.findById(id).isEmpty()) {
            model.addAttribute("id", id);
            return "not_found";
        }
        repository.deleteById(id);
        return "main";
    }

    //view all questions
    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("questions", repository.findAll());
        return "all";
    }

    @GetMapping("/random")
    public String random(Model model) {
        //get random question from repo
        List<Object[]> random = repository.getRandomQuestion();
        Object[] objects = random.get(0);

        model.addAttribute("id", objects[0]);
        model.addAttribute("question", objects[1]);
        model.addAttribute("answer", objects[2]);

        return "result";
    }

}
