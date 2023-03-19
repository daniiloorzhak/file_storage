CREATE TABLE role(
   id serial PRIMARY KEY,
   name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE account (
	id serial PRIMARY KEY,
	username VARCHAR (255) UNIQUE NOT NULL,
	password VARCHAR (512) NOT NULL
);

CREATE TABLE account_role (
  account_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (account_id, role_id),
  FOREIGN KEY (role_id) REFERENCES role (id),
  FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE catalogue (
	id serial PRIMARY KEY,
	name VARCHAR (255) NOT NULL,
	is_root BOOLEAN NOT NULL,
	is_private BOOLEAN NOT NULL
);

CREATE TABLE catalogue_child (
    parent_id INT NOT NULL,
    child_id INT NOT NULL,
    PRIMARY KEY (parent_id, child_id),
    FOREIGN KEY (parent_id) REFERENCES catalogue (id),
    FOREIGN KEY (child_id) REFERENCES catalogue (id)
);

CREATE TABLE catalogue_parent (
    child_id INT NOT NULL,
    parent_id INT NOT NULL,
    PRIMARY KEY (parent_id, child_id),
    FOREIGN KEY (parent_id) REFERENCES catalogue (id),
    FOREIGN KEY (child_id) REFERENCES catalogue (id)
);

CREATE TABLE account_catalogue (
    account_id INT NOT NULL,
    catalogue_id INT NOT NULL,
    PRIMARY KEY (account_id, catalogue_id),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (catalogue_id) REFERENCES catalogue (id)
);

CREATE TABLE file (
	id serial PRIMARY KEY,
	name VARCHAR (255) NOT NULL
);

CREATE TABLE catalogue_file (
    catalogue_id INT NOT NULL,
    file_id INT NOT NULL,
    PRIMARY KEY (catalogue_id, file_id),
    FOREIGN KEY (catalogue_id) REFERENCES catalogue (id),
    FOREIGN KEY (file_id) REFERENCES file (id)
);

CREATE TABLE account_file (
    account_id INT NOT NULL,
    file_id INT NOT NULL,
    PRIMARY KEY (account_id, file_id),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (file_id) REFERENCES file (id)
);

INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');

INSERT INTO account (username, password)
VALUES ('admin', '$2a$10$ELH7ZujJZ2YDjU8rKOgTZe4qDGEmyk4ySiQVCo05k8NtDEx84hVxe');

INSERT INTO account_role (account_id, role_id) VALUES (1, 1);
INSERT INTO account_role (account_id, role_id) VALUES (1, 2);