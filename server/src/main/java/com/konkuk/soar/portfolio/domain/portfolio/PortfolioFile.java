package com.konkuk.soar.portfolio.domain.portfolio;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.portfolio.domain.id.PortfolioAndFileId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "portfolio_files")
@IdClass(PortfolioAndFileId.class)
public class PortfolioFile {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prf_portfolio_id")
  private Portfolio portfolio;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prf_file_id")
  private File file;

  @Builder
  public PortfolioFile(Portfolio portfolio, File file) {
    this.portfolio = portfolio;
    if(!portfolio.getFileList().contains(this)) {
      portfolio.getFileList().add(this);
    }

    this.file = file;
    if (!file.getPortfolioFileList().contains(this)) {
      file.getPortfolioFileList().add(this);
    }
  }
}
