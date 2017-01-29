/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USER_PRIVACY_OPTIONS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    profile_privacy_state: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    },
    friends_request_state: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    },
    location_privacy_state: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    },
    message_privacy_state: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    }
  }, {
    tableName: 'USER_PRIVACY_OPTIONS'
  });
};
