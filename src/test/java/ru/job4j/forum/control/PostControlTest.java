package ru.job4j.forum.control;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService postService;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void ShouldReturnEditPost() throws Exception {
        this.mockMvc.perform(get("/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void ShouldReturnAddPost() throws Exception {
        this.mockMvc.perform(get("/addPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void ShouldReturnPost() throws Exception {
        this.mockMvc.perform(get("/post").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Куплю ладу-грант. Дорого.")
                .param("description", "Год выпуска не ранее 2018"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postService).savePost(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("Год выпуска не ранее 2018"));
    }

    @Test
    @WithMockUser
    public void shouldSaveCommentary() throws Exception {
        this.mockMvc.perform(post("/addComm")
                .param("id", "1")
                .param("text", "Куплю, но не дорого"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
        ArgumentCaptor<Comment> argumentComment = ArgumentCaptor.forClass(Comment.class);
        ArgumentCaptor<Integer> argumentID = ArgumentCaptor.forClass(Integer.class);
        verify(commentService).addCommentToPost(argumentID.capture(), argumentComment.capture());
        assertThat(argumentID.getValue(), is(1));
        assertThat(argumentComment.getValue().getText(), is("Куплю, но не дорого"));
    }
}