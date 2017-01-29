/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('POST_VOTES', {
    vote_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    post_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_POSTS',
        key: 'post_id'
      }
    }
  }, {
    tableName: 'POST_VOTES'
  });
};
