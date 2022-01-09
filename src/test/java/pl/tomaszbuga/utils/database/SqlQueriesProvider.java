package pl.tomaszbuga.utils.database;

public abstract class SqlQueriesProvider {
    protected static String getCategoryTitlesQuery() {
        return """
                SELECT title FROM category;
                """;
    }

    protected static String getArticlesListByCategoryIdQuery(String categoryId) {
        return String.format("""
                SELECT article.title,
                      concat(author_first_name, ' ', author_last_name) AS author_full_name,
                      article.publish_date,
                      string_agg(category.tag, ', ')                   AS category_tag_list,
                      string_agg(category.title, ', ')                 AS category_title_list
                FROM category,
                     article_category,
                    (
                        SELECT article_id AS aid
                        FROM article_category
                        WHERE category_id = %s
                    ) AS ac
                        INNER JOIN article ON article.id = ac.aid
                WHERE article.id = article_category.article_id
                AND category.id = article_category.category_id
                GROUP BY 1, 2, 3;
                """, categoryId);
    }

    protected static String getArticleDetailsQuery(String articleId) {
        return String.format("""
                SELECT article.title,
                       article.publish_date,
                       CONCAT(author_first_name, ' ', author_last_name) as author_full_name,
                       article.summary,
                       article.content
                FROM article
                WHERE article.id = %s;
                """, articleId);
    }
}
