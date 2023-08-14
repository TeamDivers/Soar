package com.konkuk.soar.common.domain;

import com.konkuk.soar.portfolio.domain.project.ProjectFile;
import com.konkuk.soar.studyhistory.domain.StudyHistoryFile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "files")
public class File {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "file_id")
  @Setter(AccessLevel.NONE)
  private Long id;

  @NotBlank
  @Length(max = 20)
  @Column(name = "file_type")
  private String type;

  @NotBlank
  @Length(max = 1000)
  @Column(name = "file_original_name")
  private String originalName;

  @NotBlank
  @Length(max = 1000)
  @Column(name = "file_saved_name")
  private String savedName;

  @NotBlank
  @Length(max = 2083)
  @Column(name = "file_url")
  private String url;

  @OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  List<ProjectFile> projectFileList = new ArrayList<>();

  @OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  List<StudyHistoryFile> studyHistoryFileList = new ArrayList<>();

  @Builder
  public File(String type, String originalName, String savedName, String url) {
    this.type = type;
    this.originalName = originalName;
    this.savedName = savedName;
    this.url = url;
  }
}
