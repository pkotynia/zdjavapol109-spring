package pl.sda.demo.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        //todo save question
        logger.info("Question: {} Answer: {}", question, answer);
        //return result view
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        return "result";
    }

    //delete question
    @PostMapping("/delete")
    public String delete(@RequestParam String id) {
        //todo delete
        return "main";
    }
    //view all questions


    @GetMapping("/all")
    public String all(Model model) {
        Question q1 = new Question(1L, "What is meaning of life", "44");
        Question q2 = new Question(2L, "Is it weekend", "No");
        model.addAttribute("questions",List.of(q1, q2));
        return "all";
    }

}
