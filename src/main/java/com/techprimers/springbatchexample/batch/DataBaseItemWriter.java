package com.techprimers.springbatchexample.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample.model.Model;
import com.techprimers.springbatchexample.repository.ModelRepository;

@Component
public class DataBaseItemWriter implements ItemWriter<Model> {
   private static final String PARAM_ID_ARQUIVO = "ID_ARQUIVO_PROCESSAMENTO_BATCH";

   private StepExecution stepExecution;
   private final ModelRepository modelRepository;

   public DataBaseItemWriter(final ModelRepository modelRepository) {
      this.modelRepository = modelRepository;
   }

   @Override
   public void write(final List<? extends Model> models) throws Exception {

      for (final Model model : models) {
         Long fileId;
         if (arquivoJaIncluido(model)) {
            fileId = stepExecution.getJobExecution().getExecutionContext().getLong(PARAM_ID_ARQUIVO);

         } else {
            fileId = modelRepository.getNextFileId();
            stepExecution.getJobExecution().getExecutionContext().put(PARAM_ID_ARQUIVO, fileId);
         }

         model.setFileId(fileId);
         modelRepository.save(model);
      }
   }

   @BeforeStep
   public void before(final StepExecution stepExecution) {
      this.stepExecution = stepExecution;
   }

   private boolean arquivoJaIncluido(final Model model) {
      return model.getLineNumber() > 1;
   }
}
