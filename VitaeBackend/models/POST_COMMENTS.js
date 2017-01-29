/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('POST_COMMENTS', {
    comment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    post_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_POSTS',
        key: 'post_id'
      }
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    text: {
      type: DataTypes.STRING,
      allowNull: false
    },
    comment_time: {
      type: DataTypes.TIME,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP')
    },
    user_ip: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'POST_COMMENTS'
  });
};
