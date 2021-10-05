
alter table purchases_223 add sbkr_file_id bigint;

alter table purchases_223 add sbkr_file_version int;

alter table purchases_223 add
  CONSTRAINT fk_purchases_223_sbkr_fileid FOREIGN KEY (sbkr_file_id, sbkr_file_version) REFERENCES files (id, version);