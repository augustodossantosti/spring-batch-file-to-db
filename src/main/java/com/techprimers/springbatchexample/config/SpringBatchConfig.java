package com.techprimers.springbatchexample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.techprimers.springbatchexample.model.Model;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

   private final Resource[] resources;

   public SpringBatchConfig(@Value("classpath:input/file*.txt") final Resource[] resources) {
      this.resources = resources;
   }

   @Bean
   public Job job(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory, final ItemWriter<Model> itemWriter) {

      final Step step = stepBuilderFactory.get("ETL-file-load")
              .<Model, Model>chunk(100)
              .reader(multiResourceItemReader())
              .writer(itemWriter)
              .build();

      return jobBuilderFactory.get("ETL-Load")
              .incrementer(new RunIdIncrementer())
              .start(step)
              .build();
   }

   @Bean
   public MultiResourceItemReader<Model> multiResourceItemReader() {
      final MultiResourceItemReader<Model> resourceItemReader = new MultiResourceItemReader<>();
      resourceItemReader.setResources(resources);
      resourceItemReader.setDelegate(fileItemReader());
      return resourceItemReader;
   }

   @Bean
   public FlatFileItemReader<Model> fileItemReader() {
      final FlatFileItemReader<Model> flatFileItemReader = new FlatFileItemReader<>();
      flatFileItemReader.setName("CSV-Reader");
      flatFileItemReader.setLinesToSkip(0);

      flatFileItemReader.setLineMapper((line, lineNumber) -> {
         final Model model = new Model();
         model.setLine(line);
         model.setLineNumber(lineNumber);
         return model;
      });
      return flatFileItemReader;
   }

}
