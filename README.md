# jpa-sample


### Init Database on local
```shell
docker volume create pgdata

docker run --name local_coupon_postgres -d \
-v pgdata:/var/lib/postgresql/data \
-p 5431:5432 \
-e POSTGRES_PASSWORD='dudtjr' \
-e POSTGRES_USER="youngseok" \
-e POSTGRES_DB="coupon_db" \
postgres
```
