CREATE TABLE pagamento (
  id_pagamento bigint NOT NULL AUTO_INCREMENT,
  codigo varchar(3) NOT NULL,
  expiracao varchar(255) DEFAULT NULL,
  forma_de_pagamento int DEFAULT NULL,
  nome varchar(100) NOT NULL,
  numero varchar(100) NOT NULL,
  pedido_id bigint NOT NULL,
  status int NOT NULL,
  valor decimal(19,2) NOT NULL,
  PRIMARY KEY (id_pagamento)
);

INSERT INTO `pagamento` VALUES (1,'212','Hoje',3,'Mariana','212',1,1,200.00),(2,'213','Hoje',0,'Mariana','213',2,1,110.00);