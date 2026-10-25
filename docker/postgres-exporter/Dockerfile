FROM prometheuscommunity/postgres-exporter:latest

COPY custom_queries.yml /customer_queries.yml

ENV PG_EXPORTER_EXTEND_QUERY_PATH="/custom_queries.yml"

