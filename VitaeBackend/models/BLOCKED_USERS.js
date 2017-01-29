/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('BLOCKED_USERS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    blocked_user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    block_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    block_reason_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'BLOCK_REASON',
        key: 'block_reason_id'
      }
    }
  }, {
    tableName: 'BLOCKED_USERS'
  });
};
