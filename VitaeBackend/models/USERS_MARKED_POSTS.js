/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USERS_MARKED_POSTS', {
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
    },
    importance_level: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    }
  }, {
    tableName: 'USERS_MARKED_POSTS'
  });
};
