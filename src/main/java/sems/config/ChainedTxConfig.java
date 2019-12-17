package sems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTxConfig {
  
  @Bean
  @Primary
  public PlatformTransactionManager transactionManager(PlatformTransactionManager custTxManager, PlatformTransactionManager lguTxManager) {
    return new ChainedTransactionManager(custTxManager, lguTxManager);
  }
}
