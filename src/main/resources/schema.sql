DROP TABLE IF EXISTS Loc_contract;
CREATE TABLE Loc_contract (
                              ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                              date_begin DATE NOT NULL,
                              date_end DATE,
                              num_contract VARCHAR(100) not null,
                              sum DECIMAL(17,4),
                              comment VARCHAR(255),
                                    constraint q unique (num_contract),
                                    constraint a check (date_begin)<=(date_end)
);