package pl.sda.demo.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/submit")
    public String submit(@RequestParam String question, @RequestParam String answer, Model model) {
        //todo save question
        logger.info("Question: {} Answer: {}", question, answer);
        //return result view
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        return "result";
    }



    //view all questions

    //delete question
}
