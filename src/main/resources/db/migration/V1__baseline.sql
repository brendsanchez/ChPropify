CREATE TABLE property (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  email_address VARCHAR(50),
  property_type enum('SINGLE_FAMILY', 'MULTI_FAMILY', 'CONDOMINIUM', 'TOWNHOUSE') DEFAULT NULL,
  rent_price FLOAT NOT NULL,
  create_time DATE,
  /*address_id INT NOT NULL,*/
  code VARCHAR(10) NOT NULL
);
