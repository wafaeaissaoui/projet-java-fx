select id,p.nameproduct,c.nameclient,`number`,prix,`status` from `order` as o inner join product as p on o.nameproduct=p.id_product inner join client as c on o.nameclient=c.id_client ;
