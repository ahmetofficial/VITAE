/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('GROUPS_REQUEST', {
    sender_user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    receiver_user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    group_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'GROUPS',
        key: 'group_id'
      }
    },
    ip: {
      type: DataTypes.STRING,
      allowNull: false
    },
    request_time: {
      type: DataTypes.TIME,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP')
    }
  }, {
    tableName: 'GROUPS_REQUEST'
  });
};
