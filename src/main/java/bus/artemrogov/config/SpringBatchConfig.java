package bus.artemrogov.config;

import bus.artemrogov.batch.DBWriter;
import bus.artemrogov.batch.NotifyCompledBatch;
import bus.artemrogov.batch.ProcessorUser;
import bus.artemrogov.batch.StartNotifyListener;
import bus.artemrogov.entity.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${input}")
    private String fileInput;

    @Bean
    public Job importUserJob(StartNotifyListener listenerStart, NotifyCompledBatch listenerEnd, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listenerStart)
                .listener(listenerEnd)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public FlatFileItemReader reader() {
        return new FlatFileItemReaderBuilder().name("usersItemReader")
                .resource(new ClassPathResource(this.fileInput))
                .delimited()
                .names(new String[] { "firstName", "lastName"})
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                    setTargetType(User.class);
                }}).build();
    }

    @Bean
    public Step step1(DBWriter writer) {
        return stepBuilderFactory.get("step1")
                .<User, User> chunk(300)
                .reader(reader())
                .processor(processorUser())
                .writer(writer)
                .build();
    }


    @Bean
    public ProcessorUser processorUser(){
        return new ProcessorUser();
    }

}
